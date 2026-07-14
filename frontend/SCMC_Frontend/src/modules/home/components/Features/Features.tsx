import "./Features.css";

import Card from "../../../../shared/components/Card";
import SectionTitle from "../../../../shared/components/SectionTitle";

import {
    Lock,
    Unlock,
    ScrollText,
    Calculator
} from "lucide-react";

const features = [

    {
        icon:<Lock/>,
        title:"Cifrado",
        description:"Aplica múltiples transformaciones matemáticas para proteger la información."
    },

    {
        icon:<Unlock/>,
        title:"Descifrado",
        description:"Recupera el mensaje original utilizando el algoritmo inverso."
    },

    {
        icon:<ScrollText/>,
        title:"Auditoría",
        description:"Visualiza paso a paso cada operación realizada durante el proceso."
    },

    {
        icon:<Calculator/>,
        title:"Fundamentos",
        description:"Explora los conceptos de Matemáticas Discretas utilizados en SCMC."
    }

];

const Features=()=>{

    return(

        <section className="section">

            <div className="container">

                <SectionTitle

                    title="Características"

                    subtitle="Todo el proyecto fue diseñado para explicar el algoritmo de forma visual e interactiva."

                />

                <div className="features-grid">

                    {

                        features.map((feature)=>(

                            <Card key={feature.title}>

                                <div className="feature">

                                    <div className="feature-icon">

                                        {feature.icon}

                                    </div>

                                    <h3>

                                        {feature.title}

                                    </h3>

                                    <p>

                                        {feature.description}

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

export default Features;