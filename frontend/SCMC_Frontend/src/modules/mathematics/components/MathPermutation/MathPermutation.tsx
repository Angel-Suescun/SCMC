import "./MathPermutation.css";

interface MathPermutationProps {

    permutation: number[];

}

export default function MathPermutation({

    permutation

}: MathPermutationProps) {

    return (

        <div className="math-permutation">

            <div className="math-row">

                <div className="math-label">

                    Posición

                </div>

                {

                    permutation.map((_, index) => (

                        <div

                            key={index}

                            className="math-cell"

                        >

                            {index + 1}

                        </div>

                    ))

                }

            </div>

            <div className="math-row">

                <div className="math-label">

                    Permutación

                </div>

                {

                    permutation.map((value, index) => (

                        <div

                            key={index}

                            className="math-cell active"

                        >

                            {value}

                        </div>

                    ))

                }

            </div>

        </div>

    );

}