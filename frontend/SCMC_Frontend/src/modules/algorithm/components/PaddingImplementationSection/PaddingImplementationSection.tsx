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

                    Antes de aplicar cualquier transformación, el mensaje debe
                    tener una longitud divisible por el tamaño del bloque.
                    Para lograrlo se agregan caracteres de relleno "~"
                    hasta completar el múltiplo requerido.

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

                            ¿Qué hace este código?

                        </h3>

                        <ul>

                            <li>
                                Crea una copia modificable del mensaje.
                            </li>

                            <li>
                                Verifica si la longitud actual es múltiplo
                                del tamaño de bloque.
                            </li>

                            <li>
                                Mientras no lo sea, agrega el carácter "~".
                            </li>

                            <li>
                                Devuelve el mensaje listo para dividirse
                                en bloques iguales.
                            </li>

                        </ul>

                    </article>

                    <article className="glass example-card">

                        <h3>

                            Ejemplo

                        </h3>

                        <div className="example-row">

                            <span>Mensaje</span>

                            <code>Hola</code>

                        </div>

                        <div className="example-row">

                            <span>Block Size</span>

                            <code>8</code>

                        </div>

                        <div className="example-row">

                            <span>Resultado</span>

                            <code>Hola~~~~</code>

                        </div>

                    </article>

                    <article className="glass math-card">

                        <h3>

                            Interpretación matemática

                        </h3>

                        <pre>

{`long = |mensaje|

mientras(long mod blockSize ≠ 0)

    mensaje = mensaje + "~"`}

                        </pre>

                    </article>

                </div>

            </div>

        </section>

    );

}