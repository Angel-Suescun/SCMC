import { useEffect, useState } from "react";
import "./Navbar.css";
import { NavLink } from "react-router-dom";

const navLinks = [
    { to: "/", label: "Inicio" },
    { to: "/encrypt", label: "Cifrar" },
    { to: "/decrypt", label: "Descifrar" },
    { to: "/mathematics", label: "Fundamentos Matemáticos" },
    { to: "/algorithm", label: "Implementación" },
];

const Navbar = () => {
    const [isOpen, setIsOpen] = useState(false);

    const closeMenu = () => setIsOpen(false);

    useEffect(() => {
        document.body.style.overflow = isOpen ? "hidden" : "";

        return () => {
            document.body.style.overflow = "";
        };
    }, [isOpen]);

    return (
        <header className="navbar">
            <div className="container navbar-container">

                <NavLink to="/" className="navbar-logo" onClick={closeMenu}>
                    SCMC
                </NavLink>

                <button
                    type="button"
                    className={`navbar-toggle ${isOpen ? "open" : ""}`}
                    aria-label={isOpen ? "Cerrar menú" : "Abrir menú"}
                    aria-expanded={isOpen}
                    onClick={() => setIsOpen((prev) => !prev)}
                >
                    <span />
                    <span />
                    <span />
                </button>

                <nav className={`navbar-menu ${isOpen ? "open" : ""}`}>
                    {navLinks.map(({ to, label }) => (
                        <NavLink
                            key={to}
                            to={to}
                            className="navbar-link"
                            onClick={closeMenu}
                        >
                            {label}
                        </NavLink>
                    ))}

                    <NavLink
                        to="/encrypt"
                        className="navbar-button navbar-button--mobile"
                        onClick={closeMenu}
                    >
                        Comenzar
                    </NavLink>
                </nav>

                <div className="navbar-actions">
                    <NavLink
                        to="/encrypt"
                        className="navbar-button"
                    >
                        Comenzar
                    </NavLink>
                </div>

            </div>
        </header>
    );
};

export default Navbar;
