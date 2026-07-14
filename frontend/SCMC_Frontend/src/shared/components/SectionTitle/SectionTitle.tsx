import "./SectionTitle.css";

interface SectionTitleProps {

    title: string;

    subtitle?: string;

    center?: boolean;

}

const SectionTitle = ({
    title,
    subtitle,
    center = true
}: SectionTitleProps) => {

    return (

        <div className={`section-title-container ${center ? "center" : ""}`}>

            <h2 className="section-title-heading">

                {title}

            </h2>

            {

                subtitle &&

                <p className="section-title-subtitle">

                    {subtitle}

                </p>

            }

        </div>

    );

};

export default SectionTitle;