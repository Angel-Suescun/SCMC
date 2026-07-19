import "./DecryptSection.css";

export default function DecryptSection() {

    return (

        <section className="decrypt-section">

            <div className="decrypt-header">

                <span>

                    Descifrado

                </span>

                <h2>

                    Recuperación del mensaje mediante transformaciones inversas

                </h2>

                <p>

                    Cada operación aplicada durante el cifrado posee una
                    transformación inversa. Gracias a las propiedades de las
                    funciones biyectivas y de la aritmética modular, el mensaje
                    original puede recuperarse exactamente sin pérdida de
                    información.

                </p>

            </div>

            <div className="decrypt-flow">

                <div className="decrypt-flow-card">

                    <span>

                        Mensaje cifrado

                    </span>

                </div>

                <div className="decrypt-flow-arrow">

                    ↓

                </div>

                <div className="decrypt-flow-card">

                    <h4>

                        Desplazamiento modular inverso

                    </h4>

                    <code>

                        (C - k + 256) mod 256

                    </code>

                </div>

                <div className="decrypt-flow-arrow">

                    ↓

                </div>

                <div className="decrypt-flow-card">

                    <h4>

                        Permutación inversa

                    </h4>

                    <div className="matrix">

                        <div>1</div>
                        <div>2</div>
                        <div>3</div>
                        <div>4</div>

                        <div>2</div>
                        <div>4</div>
                        <div>1</div>
                        <div>3</div>

                    </div>

                </div>

                <div className="decrypt-flow-arrow">

                    ↓

                </div>

                <div className="decrypt-flow-card">

                    <h4>

                        Eliminación del padding

                    </h4>

                    <code>

                        O ~ ~ ~ → O

                    </code>

                </div>

                <div className="decrypt-flow-arrow">

                    ↓

                </div>

                <div className="decrypt-flow-card success">

                    <span>

                        Mensaje original

                    </span>

                </div>

            </div>

            <div className="decrypt-properties">

                <article className="property-card glass">

                    <h3>

                        Función inversa

                    </h3>

                    <p>

                        Toda permutación posee una única permutación inversa,
                        permitiendo reconstruir el orden original de los
                        caracteres.

                    </p>

                </article>

                <article className="property-card glass">

                    <h3>

                        Modularidad

                    </h3>

                    <p>

                        El desplazamiento inverso utiliza la misma aritmética
                        modular para garantizar que cada carácter vuelva a su
                        valor ASCII inicial.

                    </p>

                </article>

                <article className="property-card glass">

                    <h3>

                        Recuperación exacta

                    </h3>

                    <p>

                        Después de eliminar el padding "~", el mensaje obtenido
                        es exactamente igual al mensaje ingresado durante el
                        proceso de cifrado.

                    </p>

                </article>

            </div>

        </section>

    );

}