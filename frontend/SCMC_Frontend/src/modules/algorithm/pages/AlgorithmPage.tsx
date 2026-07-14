import "./ImplementationPage.css";

import HeroSection from "../components/Hero/HeroSection";

import PaddingImplementationSection from "../components/PaddingImplementationSection/PaddingImplementationSection";
import PermutationImplementationSection from "../components/PermutationImplementationSection/PermutationImplementationSection";
import ModularImplementationSection from "../components/ModularImplementationSection/ModularImplementationSection";
import ShiftNormalizationSection from "../components/ShiftNormalizationSection/ShiftNormalizationSection";
import EncryptServiceSection from "../components/EncryptServiceSection/EncryptServiceSection";

export default function ImplementationPage() {

    return (

        <main className="implementation-page">

            <HeroSection />

            <PaddingImplementationSection />

            <PermutationImplementationSection />

            <ModularImplementationSection />

            <ShiftNormalizationSection />

            <EncryptServiceSection />

        </main>

    );

}