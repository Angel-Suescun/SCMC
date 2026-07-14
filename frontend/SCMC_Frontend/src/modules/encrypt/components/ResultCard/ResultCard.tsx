import "./ResultCard.css";

import { Copy, CheckCircle2 } from "lucide-react";
import { useState } from "react";

import Button from "../../../../shared/components/Button/Button";

import type { EncryptResponse } from "../../types/EncryptResponse";

import PermutationMatrix from "../PermutationMatrix/PermutationMatrix";

interface ResultCardProps {

    result?: EncryptResponse;

}

export default function ResultCard({

    result

}: ResultCardProps) {

    const [copied, setCopied] = useState(false);

    if (!result) {

        return (

            <div className="result-card glass">

                <h3>

                    Resultado

                </h3>

                <p className="result-empty">

                    Aún no se ha realizado ningún cifrado.

                </p>

            </div>

        );

    }

    async function handleCopy() {

        if (!result) {

            return;

        }

        await navigator.clipboard.writeText(

            result.encryptedMessage

        );

        setCopied(true);

        setTimeout(

            () => setCopied(false),

            2000

        );

    }

    return (

        <div className="result-card glass">

            <h3>

                Resultado

            </h3>

            <p className="result-description">

                Información generada por el algoritmo SCMC.

            </p>

            <div className="result-group">

                <label>

                    Mensaje original

                </label>

                <code>

                    {result.originalMessage}

                </code>

            </div>

            <div className="result-group">

                <label>

                    Mensaje con relleno

                </label>

                <code>

                    {result.paddedMessage}

                </code>

            </div>

            <div className="result-group">

                <label>

                    Permutación aplicada

                </label>

                <PermutationMatrix

                    permutation={result.permutation}

                />

            </div>

            <div className="result-group">

                <label>

                    Mensaje permutado

                </label>

                <code>

                    {result.permutedMessage}

                </code>

            </div>

            <div className="result-final">

                <label>

                    Mensaje cifrado

                </label>

                <code>

                    {result.encryptedMessage}

                </code>

            </div>

            <div className="result-copy">

                <Button

                    variant="outline"

                    fullWidth

                    icon={

                        copied

                            ? <CheckCircle2 size={18} />

                            : <Copy size={18} />

                    }

                    onClick={handleCopy}

                >

                    {

                        copied

                            ? "¡Copiado!"

                            : "Copiar resultado"

                    }

                </Button>

            </div>

        </div>

    );

}