import "./Button.css";

import type { ReactNode } from "react";

interface ButtonProps {

    children: ReactNode;

    onClick?: () => void;

    type?: "button" | "submit" | "reset";

    variant?: "primary" | "secondary" | "outline" | "danger";

    disabled?: boolean;

    icon?: ReactNode;

}

const Button = ({
    children,
    onClick,
    type = "button",
    variant = "primary",
    disabled = false,
    icon
}: ButtonProps) => {

    return (

        <button
            type={type}
            onClick={onClick}
            disabled={disabled}
            className={`button button-${variant}`}
        >

            {icon &&

                <span className="button-icon">

                    {icon}

                </span>

            }

            <span>

                {children}

            </span>

        </button>

    );

};

export default Button;