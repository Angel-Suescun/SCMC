import "./CodeBlock.css";

interface CodeBlockProps {

    title: string;

    code: string;

    language?: string;

}

export default function CodeBlock({

    title,

    code,

    language = "java"

}: CodeBlockProps) {

    return (

        <article className="code-block glass">

            <header className="code-block-header">

                <div className="code-block-dots">

                    <span />

                    <span />

                    <span />

                </div>

                <span className="code-language">

                    {language}
                </span>

            </header>

            <div className="code-block-title">

                {title}
            </div>

            <pre>

                <code>

                    {code}

                </code>

            </pre>

        </article>

    );

}