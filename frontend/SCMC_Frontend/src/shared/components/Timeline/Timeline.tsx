import "./Timeline.css";

interface TimelineItem {

    title: string;

    description: string;

}

interface TimelineProps {

    items: TimelineItem[];

}

export default function Timeline({

    items

}: TimelineProps) {

    return (

        <div className="timeline">

            {

                items.map((item,index)=>(

                    <div

                        key={index}

                        className="timeline-item"
                    >

                        <div className="timeline-dot" />

                        {

                            index < items.length - 1 && (

                                <div className="timeline-line" />

                            )

                        }

                        <div className="timeline-content">

                            <h3>

                                {item.title}

                            </h3>

                            <p>

                                {item.description}

                            </p>

                        </div>

                    </div>

                ))

            }

        </div>

    );

}