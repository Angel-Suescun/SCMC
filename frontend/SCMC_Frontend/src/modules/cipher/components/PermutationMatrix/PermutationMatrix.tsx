import "./PermutationMatrix.css";

interface PermutationMatrixProps {

    permutation: number[];

    editable?: boolean;

    onChange?: (index: number, value: number) => void;

}

export default function PermutationMatrix({

    permutation,

    editable = false,

    onChange

}: PermutationMatrixProps) {

    return (

        <div className="permutation-matrix">

            <div className="matrix-scroll">

                <table className="matrix-table">

                    <tbody>

                        <tr>

                            {

                                permutation.map((_, index) => (

                                    <td key={index} className="matrix-header">

                                        {index + 1}

                                    </td>

                                ))

                            }

                        </tr>

                        <tr>

                            {

                                permutation.map((value, index) => (

                                    <td key={index}>

                                        {

                                            editable ? (

                                                <input

                                                    className="matrix-input"

                                                    type="number"

                                                    min={1}

                                                    max={permutation.length}

                                                    value={value}

                                                    onChange={(e)=>

                                                        onChange?.(

                                                            index,

                                                            Number(e.target.value)

                                                        )

                                                    }

                                                />

                                            ) : (

                                                <span className="matrix-value">

                                                    {value}

                                                </span>

                                            )

                                        }

                                    </td>

                                ))

                            }

                        </tr>

                    </tbody>

                </table>

            </div>

        </div>

    );

}