import "./EncryptPage.css";

import { useState } from "react";

import EncryptForm from "../components/EncryptForm/EncryptForm";
import ResultCard from "../components/ResultCard/ResultCard";
import AuditTimeline from "../components/AuditTimeline/AuditTimeline";

import { encrypt } from "../services/EncryptService";

import type { EncryptRequest } from "../types/EncryptRequest";
import type { EncryptResponse } from "../types/EncryptResponse";

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

        catch {

            setError("No fue posible realizar el cifrado.");

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

                <EncryptForm

                    loading={loading}

                    onEncrypt={handleEncrypt}

                    onLayoutChange={setStackedLayout}

                />

                <ResultCard

                    result={result}

                />

            </section>

            {

                error && (

                    <div className="encrypt-error">

                        {error}

                    </div>

                )

            }

            <AuditTimeline

                audit={result?.audit ?? []}

            />

        </main>

    );

}