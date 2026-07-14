import "./Card.css";

import type { ReactNode } from "react";

interface CardProps{

    children:ReactNode;

    title?:string;

    className?:string;

}

const Card = ({
    children,
    title,
    className=""
}:CardProps)=>{

    return(

        <div
            className={`card ${className}`}
        >

            {

                title &&

                <h2 className="card-title">

                    {title}

                </h2>

            }

            {children}

        </div>

    );

};

export default Card;