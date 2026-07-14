import "./ModularArithmeticSection.css";

export default function ModularArithmeticSection() {

    return (

        <section className="modular-section">

            <div className="modular-header">

                <span>

                    Aritmética Modular

                </span>

                <h2>

                    Desplazamiento en Z₂₅₆

                </h2>

                <p>

                    Después de permutar cada bloque, SCMC desplaza el valor ASCII
                    de cada carácter mediante una suma módulo 256. Esto garantiza
                    que el resultado permanezca siempre dentro del conjunto de
                    caracteres posibles.

                </p>

            </div>

            <div className="modular-grid">

                <article className="glass modular-card">

                    <h3>

                        Sin desbordamiento

                    </h3>

                    <div className="formula">

                        65 + 5 = 70

                    </div>

                    <p>

                        El carácter <strong>'A'</strong> (65) desplazado cinco
                        posiciones produce el valor ASCII 70, correspondiente a
                        la letra <strong>'F'</strong>.

                    </p>

                </article>

                <article className="glass modular-card">

                    <h3>

                        Con desbordamiento

                    </h3>

                    <div className="formula">

                        (250 + 20) mod 256 = 14

                    </div>

                    <p>

                        Cuando el resultado supera 255, se aplica el operador
                        módulo para volver al inicio del rango ASCII.

                    </p>

                </article>

            </div>

        </section>

    );

}