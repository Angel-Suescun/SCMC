import "./Workflow.css";

import Card from "../../../../shared/components/Card";
import SectionTitle from "../../../../shared/components/SectionTitle";

import {
    ArrowRight,
    FileText,
    Shuffle,
    Sigma,
    Lock
} from "lucide-react";

const Workflow = () => {

    return (

        <section className="section">

            <div className="container">

                <SectionTitle
                    title="¿Cómo funciona SCMC?"
                    subtitle="El algoritmo transforma el mensaje mediante diferentes etapas matemáticas hasta obtener un texto cifrado seguro."
                />

                <div className="workflow">

                    <Card className="workflow-card">

                        <FileText className="workflow-icon"/>

                        <h3>Mensaje</h3>

                        <p>

                            Texto original ingresado por el usuario.

                        </p>

                    </Card>

                    <ArrowRight className="workflow-arrow"/>

                    <Card className="workflow-card">

                        <Shuffle className="workflow-icon"/>

                        <h3>Permutación</h3>

                        <p>

                            Reorganización de bloques mediante una función biyectiva.

                        </p>

                    </Card>

                    <ArrowRight className="workflow-arrow"/>

                    <Card className="workflow-card">

                        <Sigma className="workflow-icon"/>

                        <h3>Transformación</h3>

                        <p>

                            Aplicación de operaciones usando aritmética modular.

                        </p>

                    </Card>

                    <ArrowRight className="workflow-arrow"/>

                    <Card className="workflow-card">

                        <Lock className="workflow-icon"/>

                        <h3>Cifrado</h3>

                        <p>

                            Obtención del mensaje protegido.

                        </p>

                    </Card>

                </div>

            </div>

        </section>

    );

};

export default Workflow;