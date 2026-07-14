import "./EncryptPage.css";

import { useState } from "react";
import axios from "axios";
import { CircleAlert } from "lucide-react";

import CipherForm from "../../components/CipherForm/CipherForm";
import CipherResultCard from "../../components/CipherResultCard/CipherResultCard";
import AuditTimeline from "../../components/AuditTimeline/AuditTimeline";

import { encrypt } from "../../services/CipherService";

import type { EncryptRequest } from "../../types/EncryptRequest";
import type { EncryptResponse } from "../../types/EncryptResponse";
import type { CipherResponse } from "../../types/CipherResponse";

export default function EncryptPage() {

    const [loading, setLoading] = useState(false);

    const [result, setResult] = useState<EncryptResponse>();

    const [error, setError] = useState("");

    const [stackedLayout, setStackedLayout] = useState(false);

    async function handleEncrypt(

        request: EncryptRequest

    ) {

        try {

            setLoading(true);

            setError("");

            const response = await encrypt(request);

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

                    setError("No fue posible realizar el cifrado.");

                }

            }

            else {

                setError("No fue posible realizar el cifrado.");

            }

        }

        finally {

            setLoading(false);

        }

    }

    const cipherResult: CipherResponse | undefined = result
        ? {

            firstMessage: result.originalMessage,

            secondMessage: result.paddedMessage,

            thirdMessage: result.permutedMessage,

            resultMessage: result.encryptedMessage,

            blockSize: result.blockSize,

            permutation: result.permutation,

            shift: result.shift,

            audit: result.audit

        }
        : undefined;

    return (

        <main className="encrypt-page container">

            <section

                className={`encrypt-grid ${stackedLayout ? "stacked" : ""}`}

            >

                <CipherForm

                    mode="encrypt"

                    loading={loading}

                    onSubmit={handleEncrypt}

                    onLayoutChange={setStackedLayout}

                />

                <CipherResultCard

                    mode="encrypt"

                    result={cipherResult}

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

                audit={cipherResult?.audit ?? []}

            />

        </main>

    );

}