import { useRouter } from "next/router";
import { useState } from "react";
import { Eye, EyeOff } from "@geist-ui/icons";
import { setCookie } from "nookies";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const router = useRouter();

  const backendUrl = "http://65.0.95.86:8080/api/auth";


  /**
   * Handles login by making a POST request to /api/auth/login and redirecting
   * the user to the dashboard if the response is ok.
   * @param {React.FormEvent<HTMLFormElement>} e - The form event
   */
  const handleLogin = async (e) => {
    e.preventDefault();
    setMessage("Logging in...");
    try {
      const response = await fetch(`${backendUrl}/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
        credentials: "include",
      });
      if (response.ok) {
        setMessage("Logged In Successfully");
        const data = await response.json();
        router.push("/dashboard");
        console.log("Logged in Succesfully",data);
      }
    } catch (error) {
      console.error("Login Failed:", error);
      setMessage("Login Failed. Please Try Again");
    }
  };



  return (
    <div className="p-container flex justify-center ">
      <div className="c-container flex flex-col items-center min-h-dvh justify-center gap-10 lg:w-2/3 w-full  ">
        <h1 className="text-center text-3xl">Log into your account</h1>
        <form
          className="flex flex-col lg:w-1/2 md:w-3/6 w-10/12 border border-zinc-400 justify-center p-5 "
          onSubmit={handleLogin}
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
            className="hover:text-amber-200 border border-zinc-400 hover:border-amber-200 p-2.5 m-2 w-fit self-center"
            type="submit"
          >
            Log In
          </button>
        </form>
      </div>
    </div>
  );
}
