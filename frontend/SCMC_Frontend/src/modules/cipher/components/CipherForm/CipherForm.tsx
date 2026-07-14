import "./CipherForm.css";

import { useState } from "react";
import { Plus, Minus } from "lucide-react";

import Button from "../../../../shared/components/Button/Button";
import PermutationMatrix from "../PermutationMatrix/PermutationMatrix";

import type { EncryptRequest } from "../../types/EncryptRequest";
import type { DecryptRequest } from "../../types/DecryptRequest";

type CipherFormProps =
    | {
          mode: "encrypt";
          loading: boolean;
          onSubmit: (request: EncryptRequest) => void;
          onLayoutChange?: (stacked: boolean) => void;
      }
    | {
          mode: "decrypt";
          loading: boolean;
          onSubmit: (request: DecryptRequest) => void;
          onLayoutChange?: (stacked: boolean) => void;
      };

export default function CipherForm(props: CipherFormProps) {

    const { mode, loading, onLayoutChange } = props;

    const encrypt = mode === "encrypt";

    const [message, setMessage] = useState("");

    const [blockSize, setBlockSize] = useState(4);

    const [shift, setShift] = useState(3);

    const [permutation, setPermutation] = useState<number[]>([
        3,
        1,
        4,
        2
    ]);

    function updateLayout(size: number) {

        setBlockSize(size);

        onLayoutChange?.(size > 10);

    }

    function handlePermutationChange(

        index: number,

        value: number

    ) {

        const updated = [...permutation];

        updated[index] = value;

        setPermutation(updated);

    }

    function increaseSize() {

        const size = blockSize + 1;

        updateLayout(size);

        setPermutation(previous => [

            ...previous,

            size

        ]);

    }

    function decreaseSize() {

        if (blockSize <= 2) {

            return;

        }

        const size = blockSize - 1;

        updateLayout(size);

        setPermutation(previous => previous.slice(0, size));

    }

    function handleSubmit(

        e: React.FormEvent

    ) {

        e.preventDefault();

        const permutationRequest = permutation.map(value => value - 1);

        if (encrypt) {

            props.onSubmit({

                message,

                blockSize,

                permutation: permutationRequest,

                shift

            });

        }

        else {

            props.onSubmit({

                encryptedMessage: message,

                blockSize,

                permutation: permutationRequest,

                shift

            });

        }

    }

    return (

        <form

            className="cipher-form glass"

            onSubmit={handleSubmit}

        >

            <div className="form-header">

                <h2>

                    {

                        encrypt

                            ? "Cifrar mensaje"

                            : "Descifrar mensaje"

                    }

                </h2>

                <p>

                    {

                        encrypt

                            ? "Ingrese los parámetros necesarios para ejecutar el algoritmo SCMC."

                            : "Ingrese el mensaje cifrado y los parámetros utilizados durante el cifrado."

                    }

                </p>

            </div>

            <div className="form-group">

                <label>

                    {

                        encrypt

                            ? "Mensaje"

                            : "Mensaje cifrado"

                    }

                </label>

                <textarea

                    rows={6}

                    value={message}

                    placeholder={

                        encrypt

                            ? "Escriba el mensaje..."

                            : "Pegue el mensaje cifrado..."

                    }

                    onChange={(e) =>

                        setMessage(e.target.value)

                    }

                />

            </div>

            <div className="form-row">

                <div className="form-group">

                    <label>

                        Desplazamiento modular

                    </label>

                    <input

                        type="number"

                        value={shift}

                        onChange={(e) =>

                            setShift(Number(e.target.value))

                        }

                    />

                </div>

            </div>

            <div className="form-group">

                <div className="permutation-header">

                    <label>

                        Permutación

                    </label>

                    <div className="permutation-actions">

                        <button

                            type="button"

                            className="matrix-button"

                            disabled={blockSize <= 2}

                            onClick={decreaseSize}

                        >

                            <Minus size={18} />

                        </button>

                        <button

                            type="button"

                            className="matrix-button"

                            onClick={increaseSize}

                        >

                            <Plus size={18} />

                        </button>

                    </div>

                </div>

                <PermutationMatrix

                    editable

                    permutation={permutation}

                    onChange={handlePermutationChange}

                />

            </div>

            <Button

                type="submit"

                loading={loading}

                fullWidth

            >

                {

                    encrypt

                        ? "Cifrar mensaje"

                        : "Descifrar mensaje"

                }

            </Button>

        </form>

    );

}