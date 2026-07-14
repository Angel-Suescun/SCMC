import "./ModularImplementationSection.css";

import CodeBlock from "../../../../shared/components/CodeBlock";

const modularCode = `public String encrypt(

    String data,

    int shift

) {

    StringBuilder result = new StringBuilder();

    for (char character : data.toCharArray()) {

        int encryptedAscii =

            (character + shift) % 256;

        result.append(

            (char) encryptedAscii

        );

    }

    return result.toString();

}`;

export default function ModularImplementationSection() {

    return (

        <section className="implementation-section">

            <div className="implementation-header">

                <span>

                    Fase 3

                </span>

                <h2>

                    Cifrado Modular

                </h2>

                <p>

                    Después de la permutación, cada carácter es transformado
                    mediante una suma modular en Z₂₅₆. El desplazamiento
                    modifica los valores ASCII sin salir del rango válido
                    comprendido entre 0 y 255.

                </p>

            </div>

            <div className="implementation-grid">

                <CodeBlock
                    language="java"
                    title="ModularEncryptService.java"
                    code={modularCode}
                />

                <div className="implementation-content">

                    <article className="glass explanation-card">

                        <h3>

                            Proceso realizado

                        </h3>

                        <ul className="steps-list">

                            <li>

                                → Recorre cada carácter del mensaje.

                            </li>

                            <li>

                                → Obtiene su valor ASCII.

                            </li>

                            <li>

                                → Suma el desplazamiento configurado.

                            </li>

                            <li>

                                → Aplica módulo 256.

                            </li>

                            <li>

                                → Convierte el resultado nuevamente en un carácter.

                            </li>

                        </ul>

                    </article>

                    <article className="glass example-card">

                        <h3>

                            Ejemplo

                        </h3>

                        <div className="modular-demo">

                            <div>

                                <span>

                                    Carácter

                                </span>

                                <code>

                                    o

                                </code>

                            </div>

                            <span className="arrow">

                                ↓

                            </span>

                            <div>

                                <span>

                                    ASCII

                                </span>

                                <code>

                                    111

                                </code>

                            </div>

                            <span className="arrow">

                                ↓

                            </span>

                            <div>

                                <span>

                                    Shift

                                </span>

                                <code>

                                    +3

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

                                    114 → r

                                </code>

                            </div>

                        </div>

                        <div className="ascii-map">

                            <div>

                                o → (111 + 3) mod 256 = 114 → r

                            </div>

                            <div>

                                H → (72 + 3) mod 256 = 75 → K

                            </div>

                            <div>

                                a → (97 + 3) mod 256 = 100 → d

                            </div>

                            <div>

                                l → (108 + 3) mod 256 = 111 → o

                            </div>

                        </div>

                    </article>

                    <article className="glass math-card">

                        <h3>

                            Regla matemática

                        </h3>

                        <pre>

{`cᵢ = (mᵢ + k) mod 256`}

                        </pre>

                        <ul className="steps-list">

                            <li>

                                mᵢ → valor ASCII original.

                            </li>

                            <li>

                                k → desplazamiento aplicado.

                            </li>

                            <li>

                                cᵢ → valor ASCII cifrado.

                            </li>

                        </ul>

                        <pre>

{`200 + 150 = 350

350 mod 256 = 94`}

                        </pre>

                        <p>

                            El módulo 256 garantiza que cualquier resultado
                            permanezca dentro del conjunto ASCII extendido.

                        </p>

                    </article>

                </div>

            </div>

        </section>

    );

}