import { Link } from "react-router-dom";
import "./Footer.css";

import { BookOpen, Code2 } from "lucide-react";

import {
    FaGithub,
    FaJava,
    FaReact
} from "react-icons/fa";

import {
    SiSpringboot,
    SiVite,
    SiTypescript
} from "react-icons/si";

const Footer = () => {

    return (

        <footer className="footer">

            <div className="container footer-container">

                {/* ==================== */}

                <div className="footer-brand">

                    <h2 className="footer-logo">
                        SCMC
                    </h2>

                    <p className="footer-description">
                        Sistema de Cifrado Multi-Capa mediante Permutaciones,
                        Funciones Biyectivas y Aritmética Modular.
                    </p>

                </div>

                {/* ==================== */}

                <div className="footer-links">

                    <h3>

                        <BookOpen size={18} />

                        Navegación

                    </h3>

                    <Link to="/">Inicio</Link>

                    <Link to="/encrypt">Cifrar</Link>

                    <Link to="/decrypt">Descifrar</Link>

                    <Link to="/mathematics">
                        Fundamentos
                    </Link>

                    <Link to="/algorithm">
                        Implementación
                    </Link>

                </div>

                {/* ==================== */}

                <div className="footer-repository">

                    <h3>

                        <Code2 size={18} />

                        Código Fuente

                    </h3>

                    <a
                        href="https://github.com/Angel-Suescun/SCMC"
                        target="_blank"
                        rel="noopener noreferrer"
                        className="github-card"
                    >

                        <FaGithub
                            className="github-icon"
                        />

                        <div>

                            <strong>

                                GitHub

                            </strong>

                            <span>

                                Repositorio Oficial

                            </span>

                        </div>

                    </a>

                    <div className="footer-tech">

                        <FaJava />

                        <SiSpringboot />

                        <FaReact />

                        <SiVite />

                        <SiTypescript />

                    </div>

                </div>

            </div>

            <div className="footer-bottom">

                <div className="container footer-bottom-content">

                    <span>

                        © 2026 SCMC

                    </span>

                    <span>

                        Proyecto Académico • Matemáticas Discretas II

                    </span>

                </div>

            </div>

        </footer>

    );

};

export default Footer;