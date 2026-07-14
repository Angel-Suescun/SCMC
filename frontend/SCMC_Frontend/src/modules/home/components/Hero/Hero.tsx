import "./Hero.css";

import { ArrowDown, BookOpen } from "lucide-react";

import { Link } from "react-router-dom";

import Button from "../../../../shared/components/Button";

import {
    ShieldCheck,
    Binary,
    Code2
} from "lucide-react";

const Hero = () => {

    return (

        <section className="hero">

            <div className="container hero-container">

                {/* =========================== */}

                <div className="hero-content">

                    <span className="hero-badge">

                        Proyecto Académico • Matemáticas Discretas II

                    </span>

                    <h1 className="hero-title">

                        Sistema de

                        <span>

                            {" "}Cifrado Multi-Capa

                        </span>

                    </h1>

                    <p className="hero-description">

                        Simulación interactiva basada en permutaciones,

                        funciones biyectivas y aritmética modular,

                        permitiendo visualizar cada transformación del

                        proceso de cifrado y descifrado.

                    </p>

                    <div className="hero-buttons">

                        <Link to="/encrypt">

                            <Button
                                icon={<ArrowDown size={18}/>}
                            >

                                Comenzar

                            </Button>

                        </Link>

                        <Link to="/mathematics">

                            <Button
                                variant="outline"
                                icon={<BookOpen size={18}/>}
                            >

                                Fundamentos

                            </Button>

                        </Link>

                    </div>

                    <div className="hero-highlights">

                        <div className="hero-highlight">

                            <ShieldCheck size={18} />

                            <span>
                                Algoritmo SCMC
                            </span>

                        </div>

                        <div className="hero-highlight">

                            <Binary size={18} />

                            <span>
                                Auditoría paso a paso
                            </span>

                        </div>

                        <div className="hero-highlight">

                            <Code2 size={18} />

                            <span>
                                Implementación en Java
                            </span>

                        </div>

                    </div>

                </div>

                {/* =========================== */}

                <div className="hero-visual">

                    <div className="hero-circle"/>

                    <div className="hero-card glass">

                        <span>

                            Mensaje

                        </span>

                        <ArrowDown/>

                        <span>

                            Permutación

                        </span>

                        <ArrowDown/>

                        <span>

                            Modular

                        </span>

                        <ArrowDown/>

                        <span>

                            Cifrado

                        </span>

                    </div>

                </div>

            </div>

        </section>

    );

};

export default Hero;