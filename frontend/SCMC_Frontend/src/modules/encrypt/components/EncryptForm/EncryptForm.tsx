import "./EncryptForm.css";

import { useState } from "react";
import { Plus, Minus } from "lucide-react";

import Button from "../../../../shared/components/Button/Button";
import PermutationMatrix from "../PermutationMatrix/PermutationMatrix";

import type { EncryptRequest } from "../../types/EncryptRequest";

interface EncryptFormProps {

    loading: boolean;

    onEncrypt: (request: EncryptRequest) => void;

    onLayoutChange?: (stacked: boolean) => void;

}

export default function EncryptForm({

    loading,

    onEncrypt,

    onLayoutChange

}: EncryptFormProps) {

    const [message, setMessage] = useState("");

    const [blockSize, setBlockSize] = useState(4);

    const [shift, setShift] = useState(3);

    const [permutation, setPermutation] = useState<number[]>([3, 1, 4, 2]);

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

        if (blockSize <= 2) return;

        const size = blockSize - 1;

        updateLayout(size);

        setPermutation(previous =>

            previous.slice(0, size)

        );

    }

    function handleSubmit(

        e: React.FormEvent

    ) {

        e.preventDefault();

        onEncrypt({

            message,

            blockSize,

            permutation: permutation.map(value => value - 1),

            shift

        });

    }

    return (

        <form

            className="encrypt-form glass"

            onSubmit={handleSubmit}

        >

            <div className="form-header">

                <h2>

                    Cifrar mensaje

                </h2>

                <p>

                    Ingrese los parámetros necesarios para ejecutar el algoritmo SCMC.

                </p>

            </div>

            <div className="form-group">

                <label>

                    Mensaje

                </label>

                <textarea

                    rows={6}

                    value={message}

                    placeholder="Escriba el mensaje..."

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

                            setShift(

                                Number(e.target.value)

                            )

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

                Cifrar mensaje

            </Button>

        </form>

    );

}