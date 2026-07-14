import "./PermutationImplementationSection.css";

import CodeBlock from "../../../../shared/components/CodeBlock";


const permutationCode = `public String encrypt(

    String paddedMessage,

    List<Integer> permutation,

    Integer blockSize

) {

    List<String> blocks = splitIntoBlocks(

        paddedMessage,

        blockSize

    );

    StringBuilder result = new StringBuilder();

    for (String block : blocks) {

        result.append(

            applyPermutation(

                block,

                permutation

            )

        );

    }

    return result.toString();

}

private String applyPermutation(

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

                    Una vez que el mensaje tiene bloques del mismo tamaño,
                    cada bloque es reorganizado utilizando una permutación
                    de índices. Los caracteres no cambian de valor, únicamente
                    cambian de posición dentro del bloque.

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

                            ¿Qué hace este código?

                        </h3>

                        <ul>

                            <li>

                                Divide el mensaje en bloques del tamaño definido.

                            </li>

                            <li>

                                Recorre cada bloque de forma independiente.

                            </li>

                            <li>

                                Utiliza la lista de índices para determinar
                                el nuevo orden de los caracteres.

                            </li>

                            <li>

                                Construye un nuevo bloque reorganizado.

                            </li>

                            <li>

                                Une todos los bloques para obtener el mensaje
                                permutado.

                            </li>

                        </ul>

                    </article>

                    <article className="glass example-card">

                        <h3>

                            Ejemplo

                        </h3>

                        <div className="example-row">

                            <span>Bloque</span>

                            <code>Hola</code>

                        </div>

                        <div className="example-row">

                            <span>Permutación</span>

                            <code>[1,0,3,2]</code>

                        </div>

                        <div className="example-row">

                            <span>Resultado</span>

                            <code>oHal</code>

                        </div>

                    </article>

                    <article className="glass example-card">

                        <h3>

                            Aplicación de índices

                        </h3>

                        <pre>

{`posición 0 → bloque[1] = o

posición 1 → bloque[0] = H

posición 2 → bloque[3] = a

posición 3 → bloque[2] = l

Resultado → oHal`}

                        </pre>

                    </article>

                    <article className="glass math-card">

                        <h3>

                            Interpretación matemática

                        </h3>

                        <pre>

{`nuevo_bloque[i]

=

bloque_original[permutacion[i]]`}

                        </pre>

                        <p>

                            La transformación corresponde a una permutación
                            de posiciones. Los elementos permanecen iguales,
                            pero ocupan nuevas ubicaciones dentro del bloque.

                        </p>

                    </article>

                </div>

            </div>

        </section>

    );

}