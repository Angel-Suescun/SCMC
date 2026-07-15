import "./CipherResultCard.css";

import { useState } from "react";
import { Copy, CheckCircle2 } from "lucide-react";

import Button from "../../../../shared/components/Button/Button";
import SafeMessage from "../../../../shared/components/SafeMessage/SafeMessage";
import PermutationMatrix from "../PermutationMatrix/PermutationMatrix";

import type { EncryptResponse } from "../../types/EncryptResponse";
import type { DecryptResponse } from "../../types/DecryptResponse";

type CipherResultCardProps =
    | {
          mode: "encrypt";
          result?: EncryptResponse;
      }
    | {
          mode: "decrypt";
          result?: DecryptResponse;
      };

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

    const encrypt = mode === "encrypt";

    const firstMessage = encrypt
        ? result.originalMessage
        : result.encryptedMessage;

    const secondMessage = encrypt
        ? result.paddedMessage
        : result.permutedMessage;

    const thirdMessage = encrypt
        ? result.permutedMessage
        : result.paddedMessage;

    const finalMessage = encrypt
        ? result.encryptedMessage
        : result.decryptedMessage;

    async function handleCopy() {

        await navigator.clipboard.writeText(finalMessage);

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

                        message={firstMessage}

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

                        message={secondMessage}

                    />

                </code>

            </div>

            <div className="result-group">

                <label>

                    Permutación aplicada

                </label>

                <PermutationMatrix

                    permutation={result.permutation.map((value) => value + 1)}

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

                        message={thirdMessage}

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

                        message={finalMessage}

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