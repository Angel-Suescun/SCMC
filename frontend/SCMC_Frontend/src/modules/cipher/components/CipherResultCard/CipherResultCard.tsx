import "./CipherResultCard.css";

import { useState } from "react";
import { Copy, CheckCircle2 } from "lucide-react";

import Button from "../../../../shared/components/Button/Button";
import SafeMessage from "../../../../shared/components/SafeMessage/SafeMessage";
import PermutationMatrix from "../PermutationMatrix/PermutationMatrix";

import type { CipherResponse } from "../../types/CipherResponse";

interface CipherResultCardProps {

    mode: "encrypt" | "decrypt";

    result?: CipherResponse;

}

export default function CipherResultCard({

    mode,

    result

}: CipherResultCardProps) {

    const [copied, setCopied] = useState(false);

    if (!result) {

        return (

            <div className="result-card glass">

                <h3>

                    Resultado

                </h3>

                <p className="result-empty">

                    Aún no se ha realizado ninguna operación.

                </p>

            </div>

        );

    }

    const currentResult = result;

    const encrypt = mode === "encrypt";

    async function handleCopy() {

        await navigator.clipboard.writeText(

            currentResult.resultMessage

        );

        setCopied(true);

        setTimeout(() => {

            setCopied(false);

        }, 2000);

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

                    {

                        encrypt

                            ? "Mensaje original"

                            : "Mensaje cifrado"

                    }

                </label>

                <code>

                    <SafeMessage

                        message={currentResult.firstMessage}

                    />

                </code>

            </div>

            <div className="result-group">

                <label>

                    {

                        encrypt

                            ? "Mensaje con relleno"

                            : "Mensaje con desplazamiento modular inverso"

                    }

                </label>

                <code>

                    <SafeMessage

                        message={currentResult.secondMessage}

                    />

                </code>

            </div>

            <div className="result-group">

                <label>

                    Permutación aplicada

                </label>

                <PermutationMatrix

                    permutation={currentResult.permutation}

                />

            </div>

            <div className="result-group">

                <label>

                    {

                        encrypt

                            ? "Mensaje permutado"

                            : "Mensaje despermutado"

                    }

                </label>

                <code>

                    <SafeMessage

                        message={currentResult.thirdMessage}

                    />

                </code>

            </div>

            <div className="result-final">

                <label>

                    {

                        encrypt

                            ? "Mensaje cifrado"

                            : "Mensaje descifrado"

                    }

                </label>

                <code>

                    <SafeMessage

                        message={currentResult.resultMessage}

                    />

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