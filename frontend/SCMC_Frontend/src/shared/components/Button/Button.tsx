import "./Button.css";

import type { ReactNode } from "react";

interface ButtonProps {

    children: ReactNode;

    onClick?: () => void;

    type?: "button" | "submit" | "reset";

    variant?: "primary" | "secondary" | "outline" | "danger";

    disabled?: boolean;

    loading?: boolean;

    fullWidth?: boolean;

    icon?: ReactNode;

}

const Button = ({

    children,

    onClick,

    type = "button",

    variant = "primary",

    disabled = false,

    loading = false,

    fullWidth = false,

    icon

}: ButtonProps) => {

    return (

        <button

            type={type}

            onClick={onClick}

            disabled={disabled || loading}

            className={`
                button
                button-${variant}
                ${fullWidth ? "button-full" : ""}
                ${loading ? "button-loading" : ""}
            `}

        >

            {

                loading ? (

                    <span className="button-spinner"/>

                ) : (

                    icon && (

                        <span className="button-icon">

                            {icon}

                        </span>

                    )

                )

            }

            <span>

                {

                    loading

                        ? "Cargando..."

                        : children

                }

            </span>

        </button>

    );

};

export default Button;