import Link from "next/link";
import { useRouter } from "next/router";
import { User } from "@geist-ui/icons";
import { useEffect, useState } from "react";
import LogoutButton from "./LogoutButton";

export default function Navbar() {
  const router = useRouter();
  const [isLogin, setIsLogin] = useState(false);
  useEffect(() => {
    /**
     * Checks if the user is authenticated by making a GET request to /api/auth/check.
     * If the response is ok, sets isLogin to true, otherwise sets it to false.
     * Also sets isLogin to false if the request fails.
     */
    const checkAuth = async () => {
      try {
        const res = await fetch("http://<your-ec2-public-ip>:8080/api/auth/check", {
          method: "GET",
          credentials: "include", // send cookies
        });

        if (res.ok) {
          setIsLogin(true);
        } else {
          setIsLogin(false);
        }
      } catch (err) {
        console.error("Auth check failed", err);
        setIsLogin(false);
      }
    };

    checkAuth();
  }, [router.pathname]);

  /**
   * Handles logout by making a POST request to /api/auth/logout and redirecting
   * the user to the homepage.
   */
  
  return (
    <div className="absolute top-0 right-0 left-0 w-full h-20 flex items-center justify-between px-4 md:px-10   transition shadow-lg  ">
      <h1 className="text-4xl font-bold   ">
        <Link href="/">FileShifter</Link>
      </h1>
      <div className="flex items-center min-w-fit">
        {!isLogin ? (
          <div className="flex items-center space-x-4">
            {router.pathname === "/register" ? (
              <Link href="/login">
                <span className="text-xl font-bold hover:text-amber-200 cursor-pointer">
                  Login
                </span>
              </Link>
            ) : router.pathname === "/login" ? (
              <Link href="/register">
                <span className="text-xl font-bold hover:text-amber-200 cursor-pointer">
                  Register
                </span>
              </Link>
            ) : (
              // For any other public page (e.g., homepage)
              <>
                <Link href="/login">
                  <span className="text-xl font-bold hover:text-amber-200 cursor-pointer">
                    Login
                  </span>
                </Link>
                <Link href="/register">
                  <span className="text-xl font-bold hover:text-amber-200 cursor-pointer">
                    Register
                  </span>
                </Link>
              </>
            )}
          </div>
        ) : (
          <div className="flex gap-5 mr-10 items-center justify-between">
            <Link href="/dashboard" passHref>
              <User className="hover:text-amber-200 cursor-pointer" />
            </Link>
            <LogoutButton></LogoutButton>
          </div>
        )}
      </div>
    </div>
  );
}
