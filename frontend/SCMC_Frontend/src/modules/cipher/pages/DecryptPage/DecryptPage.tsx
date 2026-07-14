import "./DecryptPage.css";

import { useState } from "react";
import axios from "axios";
import { CircleAlert } from "lucide-react";

import CipherForm from "../../components/CipherForm/CipherForm";
import CipherResultCard from "../../components/CipherResultCard/CipherResultCard";
import AuditTimeline from "../../components/AuditTimeline/AuditTimeline";

import { decrypt } from "../../services/CipherService";

import type { CipherResponse } from "../../types/CipherResponse";
import type { DecryptRequest } from "../../types/DecryptRequest";
import type { DecryptResponse } from "../../types/DecryptResponse";

export default function DecryptPage() {

    const [loading, setLoading] = useState(false);

    const [result, setResult] = useState<DecryptResponse>();

    const [error, setError] = useState("");

    const [stackedLayout, setStackedLayout] = useState(false);

    async function handleDecrypt(

        request: DecryptRequest

    ) {

        try {

            setLoading(true);

            setError("");

            const response = await decrypt(request);

            setResult(response);

        }

        catch (error) {

            if (axios.isAxiosError(error)) {

                const response = error.response?.data;

                if (typeof response === "string") {

                    setError(response);

                }

                else if (response?.message) {

                    setError(response.message);

                }

                else {

                    setError("No fue posible realizar el descifrado.");

                }

            }

            else {

                setError("No fue posible realizar el descifrado.");

            }

        }

        finally {

            setLoading(false);

        }

    }

    return (

        <main className="encrypt-page container">

            <section

                className={`encrypt-grid ${stackedLayout ? "stacked" : ""}`}

            >

                <CipherForm

                    mode="decrypt"

                    loading={loading}

                    onSubmit={handleDecrypt}

                    onLayoutChange={setStackedLayout}

                />

                <CipherResultCard

                    mode="decrypt"

                    result={result as unknown as CipherResponse | undefined}

                />

            </section>

            {

                error && (

                    <div className="encrypt-error">

                        <CircleAlert size={18} />

                        <span>

                            {error}

                        </span>

                    </div>

                )

            }

            <AuditTimeline

                audit={result?.audit ?? []}

            />

        </main>

    );

}