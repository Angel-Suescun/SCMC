import "./MathematicsPage.css";

import HeroSection from "../components/Hero/HeroSection";
import PermutationFlowSection from "../components/PermutationFlowSection/PermutationFlowSection";
import PaddingSection from "../components/PaddingSection/PaddingSection";
import PermutationSection from "../components/PermutationSection/PermutationSection";
import ModularArithmeticSection from "../components/ModularArithmeticSection/ModularArithmeticSection";
import ComplexitySection from "../components/ComplexitySection/ComplexitySection";
import DecryptSection from "../components/DecryptSection/DecryptSection";

export default function MathematicsPage(){

    return(

        <main className="mathematics-page container">

            <HeroSection/>

            <PermutationFlowSection/>

            <PaddingSection/>

            <PermutationSection/>

            <ModularArithmeticSection/>

            <ComplexitySection/>

            <DecryptSection/>

        </main>

    );

}