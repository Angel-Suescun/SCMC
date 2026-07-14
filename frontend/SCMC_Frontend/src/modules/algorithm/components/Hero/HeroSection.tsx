import "./HeroSection.css";

export default function HeroSection() {

    return (

        <section className="implementation-hero">

            <div className="implementation-hero__content">

                <span className="implementation-hero__label">

                    Implementación del algoritmo

                </span>

                <h1>

                    Construcción del sistema SCMC

                </h1>

                <p>

                    La implementación del algoritmo SCMC se divide en varios
                    servicios especializados. Cada componente se encarga de una
                    transformación específica del mensaje, permitiendo mantener
                    una arquitectura modular, clara y fácil de mantener.

                </p>

                <div className="implementation-hero__tags">

                    <span>Spring Boot</span>

                    <span>React</span>

                    <span>Permutaciones</span>

                    <span>Z₂₅₆</span>

                </div>

            </div>

            <div className="implementation-hero__visual glass">

                <div className="flow-node">

                    Mensaje

                </div>

                <div className="flow-arrow">

                    ↓

                </div>

                <div className="flow-node">

                    Padding

                </div>

                <div className="flow-arrow">

                    ↓

                </div>

                <div className="flow-node">

                    Permutación

                </div>

                <div className="flow-arrow">

                    ↓

                </div>

                <div className="flow-node">

                    Modular

                </div>

                <div className="flow-arrow">

                    ↓

                </div>

                <div className="flow-node result">

                    Resultado

                </div>

            </div>

        </section>

    );

}