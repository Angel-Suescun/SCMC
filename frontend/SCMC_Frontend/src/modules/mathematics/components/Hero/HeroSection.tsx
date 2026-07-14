import "./HeroSection.css";

export default function HeroSection() {

    return (

        <section className="hero-section">

            <div className="hero-content">

                <span>

                    Fundamentos Matemáticos

                </span>

                <h1>

                    La matemática detrás del algoritmo SCMC

                </h1>

                <p>

                    SCMC combina aritmética modular, teoría de permutaciones y
                    división en bloques para transformar un mensaje de forma
                    reversible. Cada operación posee una base matemática que
                    garantiza un cifrado determinista y un proceso de
                    descifrado completamente inverso.

                </p>

                <div className="hero-tags">

                    <div>

                        Z₂₅₆

                    </div>

                    <div>

                        Permutaciones

                    </div>

                    <div>

                        Bloques

                    </div>

                </div>

            </div>

            <div className="hero-visual">

                <div className="binary-card">

                    <div className="binary-row">

                        <div>1010</div>

                        <div>0111</div>

                        <div>1100</div>

                        <div>0001</div>

                    </div>

                    <p>

                        P(x) → (x + k) mod 256

                    </p>

                </div>

            </div>

        </section>

    );

}