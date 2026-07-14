import "./ShiftNormalizationSection.css";

import CodeBlock from "../../../../shared/components/CodeBlock";

const normalizeCode = `public Integer normalizeShift(Integer shift) {

    return shift % 256;

}`;

export default function ShiftNormalizationSection() {

    return (

        <section className="implementation-section">

            <div className="implementation-header">

                <span>

                    Preparación del desplazamiento

                </span>

                <h2>

                    Normalización del Shift

                </h2>

                <p>

                    Antes del cifrado modular, el desplazamiento se reduce
                    utilizando módulo 256 para trabajar dentro del conjunto
                    Z₂₅₆, que representa todos los valores ASCII posibles.

                </p>

            </div>

            <div className="implementation-grid">

                <CodeBlock
                    language="java"
                    title="ShiftService.java"
                    code={normalizeCode}
                />

                <div className="implementation-content">

                    <article className="glass explanation-card">

                        <h3>

                            ¿Qué hace este método?

                        </h3>

                        <ul>

                            <li>

                                → Recibe el valor de desplazamiento.

                            </li>

                            <li>

                                → Aplica la operación módulo 256.

                            </li>

                            <li>

                                → Obtiene un valor equivalente dentro de Z₂₅₆.

                            </li>

                            <li>

                                → Evita desplazamientos innecesariamente grandes.

                            </li>

                        </ul>

                    </article>

                    <article className="glass example-card">

                        <h3>

                            Ejemplo

                        </h3>

                        <div className="example-row">

                            <span>

                                Shift original

                            </span>

                            <code>

                                300

                            </code>

                        </div>

                        <div className="example-row">

                            <span>

                                Operación

                            </span>

                            <code>

                                300 mod 256

                            </code>

                        </div>

                        <div className="example-row">

                            <span>

                                Shift efectivo

                            </span>

                            <code>

                                44

                            </code>

                        </div>

                    </article>

                    <article className="glass math-card">

                        <h3>

                            Modelo matemático

                        </h3>

                        <pre>

{`k = shift mod 256`}

                        </pre>

                        <p>

                            Dos desplazamientos son equivalentes si producen
                            el mismo residuo módulo 256.

                        </p>

                        <pre>

{`300 ≡ 44 (mod 256)`}

                        </pre>

                    </article>

                </div>

            </div>

        </section>

    );

}