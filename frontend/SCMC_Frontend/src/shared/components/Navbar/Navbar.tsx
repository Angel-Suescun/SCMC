import "./Navbar.css";
import { NavLink } from "react-router-dom";

const Navbar = () => {
    return (
        <header className="navbar">
            <div className="container navbar-container">

                <NavLink to="/" className="navbar-logo">
                    SCMC
                </NavLink>

                <nav className="navbar-menu">

                    <NavLink to="/" className="navbar-link">
                        Inicio
                    </NavLink>

                    <NavLink to="/encrypt" className="navbar-link">
                        Cifrar
                    </NavLink>

                    <NavLink to="/decrypt" className="navbar-link">
                        Descifrar
                    </NavLink>

                    <NavLink to="/mathematics" className="navbar-link">
                        Fundamentos Matemáticos
                    </NavLink>

                    <NavLink to="/algorithm" className="navbar-link">
                        Implementación
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