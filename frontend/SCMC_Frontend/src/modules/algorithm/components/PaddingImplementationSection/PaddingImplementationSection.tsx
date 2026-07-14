import "./PaddingImplementationSection.css";

import CodeBlock from "../../../../shared/components/CodeBlock";

const paddingCode = `public String encrypt(String message, Integer blockSize) {

    StringBuilder paddedMessage = new StringBuilder(message);

    while (paddedMessage.length() % blockSize != 0) {

        paddedMessage.append('~');

    }

    return paddedMessage.toString();

}`;

export default function PaddingImplementationSection() {

    return (

        <section className="implementation-section">

            <div className="implementation-header">

                <span>

                    Fase 1

                </span>

                <h2>

                    Padding (Acolchamiento)

                </h2>

                <p>

                    Antes de dividir el mensaje en bloques, su longitud debe
                    ser múltiplo del tamaño definido. Para lograrlo, se agregan
                    caracteres de relleno "~" hasta completar el bloque.

                </p>

            </div>

            <div className="implementation-grid">

                <CodeBlock
                    language="java"
                    title="PaddingEncryptService.java"
                    code={paddingCode}
                />

                <div className="implementation-content">

                    <article className="glass explanation-card">

                        <h3>

                            Proceso realizado

                        </h3>

                        <ul className="steps-list">

                            <li>

                                → Calcula la longitud actual del mensaje.

                            </li>

                            <li>

                                → Comprueba si es múltiplo del tamaño de bloque.

                            </li>

                            <li>

                                → Agrega "~" mientras sea necesario.

                            </li>

                            <li>

                                → Devuelve un mensaje con bloques completos.

                            </li>

                        </ul>

                    </article>

                    <article className="glass example-card">

                        <h3>

                            Ejemplo

                        </h3>

                        <div className="padding-demo">

                            <div>

                                <span>

                                    Mensaje

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

                                    Block Size

                                </span>

                                <code>

                                    8

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

                                    Hola~~~~

                                </code>

                            </div>

                        </div>

                    </article>

                    <article className="glass math-card">

                        <h3>

                            Regla matemática

                        </h3>

                        <pre>

{`|mensaje| mod blockSize ≠ 0

↓

agregar "~"

↓

repetir hasta completar el bloque`}

                        </pre>

                    </article>

                </div>

            </div>

        </section>

    );

}