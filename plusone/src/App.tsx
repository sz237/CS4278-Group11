import { Routes, Route, Navigate } from "react-router-dom";
import Landing from "./pages/Landing";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Home from "./pages/Home";
import MyPage from "./pages/MyPage";
import MakePost from "./pages/MakePost";
import "./App.css";

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Landing />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
      {/* catch-all -> home */}
      <Route path="/home" element={<Home />} />
      <Route path="*" element={<Navigate to="/" replace />} />
      <Route path="/mypage" element={<MyPage />} />
        <Route path="/makepost" element={<MakePost />} /> {/* create or edit */}
    </Routes>
  );
}