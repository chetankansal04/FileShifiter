import { useRouter } from "next/router";
import { useState } from "react";

import { Eye, EyeOff } from "@geist-ui/icons";

export default function RegisterPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const backendUrl = "http://localhost:8080/api/auth";

  const router = useRouter();

  const handleRegister = async (e) => {
    e.preventDefault();
    setMessage("Signing Up...");
    try {
      const response = await fetch(`${backendUrl}/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });
      if (response.ok) {
        setMessage("Signed Up Successfully! Redirecting to Login Page...");
        setTimeout(() => {
          router.push("/login");
        }, 1500);
      } else {
        const text = await response.text();
        setMessage(text || "Sign Up Failed. Please Try Again");
      }
    } catch (error) {
      console.error("Sign Up Failed:", error);
      setMessage("Sign Up Failed. Please Try Again");
    }
  };

  return (
    <div className="p-container flex flex-col justify-center  h-dvh items-center  ">
      <div className="c-container flex flex-col items-center justify-center gap-10 lg:w-2/3 w-full   ">
        <h1 className="text-center text-3xl">Sign Up</h1>
        <form
          className="flex flex-col lg:w-1/2 md:w-3/6 w-10/12 border border-zinc-400  justify-center p-5"
          onSubmit={handleRegister}
        >
          <input
            className="border border-zinc-400  p-2.5 m-2"
            type="email"
            placeholder="Email"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <div className="flex items-center justify-around relative">
            <input
              className="border border-zinc-400 p-2.5 m-2 w-full"
              type={showPassword ? "text" : "password"}
              placeholder="Password"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {showPassword ? (
              <Eye
                className="absolute right-6  "
                onClick={() => {
                  setShowPassword(!showPassword);
                }}
              />
            ) : (
              <EyeOff
                className="absolute right-6 "
                onClick={() => {
                  setShowPassword(!showPassword);
                }}
              />
            )}
          </div>
          <button
            className="border hover:border-amber-200 hover:text-amber-200 border-zinc-400  p-2.5 m-2 w-fit self-center"
            type="submit"
          >
            Register
          </button>
        </form>
      </div>
    </div>
  );
}
