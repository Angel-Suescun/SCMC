import { BrowserRouter, Routes, Route } from "react-router-dom";

import MainLayout from "../shared/layout/MainLayout";

import HomePage from "../modules/home/pages/HomePage";
import EncryptPage from "../modules/cipher/pages/EncryptPage/EncryptPage";
import DecryptPage from "../modules/cipher/pages/DecryptPage/DecryptPage";
import MathematicsPage from "../modules/mathematics/pages/MathematicsPage";
import AlgorithmPage from "../modules/algorithm/pages/AlgorithmPage";

const AppRouter = () => {

    return (

        <BrowserRouter>

            <Routes>

                <Route element={<MainLayout />}>

                    <Route
                        path="/"
                        element={<HomePage />}
                    />

                    <Route
                        path="/encrypt"
                        element={<EncryptPage />}
                    />

                    <Route
                        path="/decrypt"
                        element={<DecryptPage />}
                    />

                    <Route
                        path="/mathematics"
                        element={<MathematicsPage />}
                    />

                    <Route
                        path="/algorithm"
                        element={<AlgorithmPage />}
                    />

                </Route>

            </Routes>

        </BrowserRouter>

    );

};

export default AppRouter;