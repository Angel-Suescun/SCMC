import "./Technologies.css";

import Card from "../../../../shared/components/Card";
import SectionTitle from "../../../../shared/components/SectionTitle";

import {
    FaJava,
    FaReact
} from "react-icons/fa";

import {
    SiSpringboot,
    SiVite,
    SiTypescript
} from "react-icons/si";

const technologies = [

    {
        icon: <FaJava />,
        title: "Java",
        description: "Implementación del algoritmo SCMC."
    },

    {
        icon: <SiSpringboot />,
        title: "Spring Boot",
        description: "API REST para cifrado y descifrado."
    },

    {
        icon: <FaReact />,
        title: "React",
        description: "Interfaz moderna e interactiva."
    },

    {
        icon: <SiVite />,
        title: "Vite",
        description: "Compilación rápida del proyecto."
    },

    {
        icon: <SiTypescript />,
        title: "TypeScript",
        description: "Tipado estático y código robusto."
    }

];

const Technologies = () => {

    return (

        <section className="section">

            <div className="container">

                <SectionTitle

                    title="Tecnologías"

                    subtitle="Herramientas utilizadas para el desarrollo del Sistema de Cifrado Multi-Capa."

                />

                <div className="tech-grid">

                    {

                        technologies.map((tech) => (

                            <Card key={tech.title}>

                                <div className="tech-card">

                                    <div className="tech-icon">

                                        {tech.icon}

                                    </div>

                                    <h3>

                                        {tech.title}

                                    </h3>

                                    <p>

                                        {tech.description}

                                    </p>

                                </div>

                            </Card>

                        ))

                    }

                </div>

            </div>

        </section>

    );

};

export default Technologies;