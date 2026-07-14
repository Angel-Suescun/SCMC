import "./PermutationImplementationSection.css";

import CodeBlock from "../../../../shared/components/CodeBlock";

const permutationCode = `public String encrypt(

    String block,

    List<Integer> permutation

) {

    StringBuilder result = new StringBuilder();

    for (Integer index : permutation) {

        result.append(

            block.charAt(index)

        );

    }

    return result.toString();

}`;

export default function PermutationImplementationSection() {

    return (

        <section className="implementation-section">

            <div className="implementation-header">

                <span>

                    Fase 2

                </span>

                <h2>

                    Permutación (Reordenamiento)

                </h2>

                <p>

                    Después del padding, cada bloque es reorganizado
                    mediante una permutación de índices. Los caracteres
                    conservan su valor original, pero cambian de posición
                    dentro del bloque.

                </p>

            </div>

            <div className="implementation-grid">

                <CodeBlock
                    language="java"
                    title="PermutationEncryptService.java"
                    code={permutationCode}
                />

                <div className="implementation-content">

                    <article className="glass explanation-card">

                        <h3>

                            Proceso realizado

                        </h3>

                        <ul className="steps-list">

                            <li>

                                → Toma un bloque de caracteres.

                            </li>

                            <li>

                                → Lee la secuencia de permutación.

                            </li>

                            <li>

                                → Reubica cada carácter según su índice.

                            </li>

                            <li>

                                → Construye un nuevo bloque ordenado.

                            </li>

                            <li>

                                → Devuelve el bloque permutado.

                            </li>

                        </ul>

                    </article>

                    <article className="glass example-card">

                        <h3>

                            Ejemplo

                        </h3>

                        <div className="permutation-demo">

                            <div>

                                <span>

                                    Bloque

                                </span>

                                <code>

                                    Hola

                                </code>

                            </div>

                            <span className="arrow">

                                ↓

                            </span>

                            <div>

                                <span>

                                    Permutación

                                </span>

                                <code>

                                    [1,0,3,2]

                                </code>

                            </div>

                            <span className="arrow">

                                ↓

                            </span>

                            <div>

                                <span>

                                    Resultado

                                </span>

                                <code>

                                    oHal

                                </code>

                            </div>

                        </div>

                    </article>

                    <article className="glass math-card">

                        <h3>

                            Regla de transformación

                        </h3>

                        <pre>

{`nuevoBloque[i] = bloque[permutacion[i]]`}

                        </pre>

                        <div className="index-map">

                            <div>

                                0 → bloque[1] = o

                            </div>

                            <div>

                                1 → bloque[0] = H

                            </div>

                            <div>

                                2 → bloque[3] = a

                            </div>

                            <div>

                                3 → bloque[2] = l

                            </div>

                        </div>

                    </article>

                </div>

            </div>

        </section>

    );

}