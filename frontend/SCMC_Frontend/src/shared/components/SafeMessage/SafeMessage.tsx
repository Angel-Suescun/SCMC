import "./SafeMessage.css";

interface SafeMessageProps {

    message: string;

}

const CONTROL_LABELS: Record<number, string> = {

    0: "NUL",
    1: "SOH",
    2: "STX",
    3: "ETX",
    4: "EOT",
    5: "ENQ",
    6: "ACK",
    7: "BEL",
    8: "BS",
    9: "TAB",
    10: "LF",
    11: "VT",
    12: "FF",
    13: "CR",
    14: "SO",
    15: "SI",
    16: "DLE",
    17: "DC1",
    18: "DC2",
    19: "DC3",
    20: "DC4",
    21: "NAK",
    22: "SYN",
    23: "ETB",
    24: "CAN",
    25: "EM",
    26: "SUB",
    27: "ESC",
    28: "FS",
    29: "GS",
    30: "RS",
    31: "US",
    127: "DEL"

};

export default function SafeMessage({

    message

}: SafeMessageProps){

    return(

        <div className="safe-message">

            {

                [...message].map((char,index)=>{

                    const code=char.charCodeAt(0);

                    const isControl=

                        code<32 ||

                        code===127;

                    const isReplacement=

                        char==="�";

                    if(isControl){

                        return(

                            <span

                                key={index}

                                className="safe-char-control"

                                title={`ASCII ${code}`}

                            >

                                {

                                    CONTROL_LABELS[code] ??

                                    `ASCII ${code}`

                                }

                            </span>

                        );

                    }

                    if(isReplacement){

                        return(

                            <span

                                key={index}

                                className="safe-char-invalid"

                                title="Carácter no representable"

                            >

                                INV

                            </span>

                        );

                    }

                    return(

                        <span

                            key={index}

                            className="safe-char"

                        >

                            {

                                char===" "

                                    ? "\u00A0"

                                    : char

                            }

                        </span>

                    );

                })

            }

        </div>

    );

}