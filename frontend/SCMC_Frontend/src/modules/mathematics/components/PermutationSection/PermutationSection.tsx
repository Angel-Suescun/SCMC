import "./PermutationSection.css";

import MathPermutation from "../MathPermutation/MathPermutation";

export default function PermutationSection() {

    return (

        <section className="permutation-section">

            <div className="permutation-header">

                <span>

                    Permutaciones

                </span>

                <h2>

                    Reorganización mediante funciones biyectivas

                </h2>

                <p>

                    Una permutación es una función biyectiva que reorganiza los
                    elementos de un conjunto sin perder información. En SCMC,
                    cada bloque del mensaje cambia el orden de sus caracteres
                    utilizando una permutación válida.

                </p>

            </div>

            <div className="permutation-content">

                <div className="permutation-description">

                    <h3>

                        Ejemplo

                    </h3>

                    <p>

                        La permutación

                    </p>

                    <code>

                        [3, 1, 4, 2]

                    </code>

                    <p>

                        indica que:

                    </p>

                    <ul>

                        <li>

                            La posición <strong>1</strong> pasa a la posición <strong>3</strong>

                        </li>

                        <li>

                            La posición <strong>2</strong> pasa a la posición <strong>1</strong>

                        </li>

                        <li>

                            La posición <strong>3</strong> pasa a la posición <strong>4</strong>

                        </li>

                        <li>

                            La posición <strong>4</strong> pasa a la posición <strong>2</strong>

                        </li>

                    </ul>

                    <p>

                        Como cada posición aparece exactamente una vez,
                        siempre es posible construir la permutación inversa
                        para recuperar el mensaje original.

                    </p>

                </div>

                <MathPermutation
                    permutation={[3,1,4,2]}
                />

            </div>

        </section>

    );

}