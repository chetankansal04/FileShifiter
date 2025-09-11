import Link from "next/link";
import { useRouter } from "next/router";
import { User } from "@geist-ui/icons";
import { useEffect, useState } from "react";
import LogoutButton from "./LogoutButton";

export default function Navbar({ isLoggedIn }) {
  const router = useRouter();

  return (
    <div className="fixed transition-all duration-300 top-0 w-full h-20 flex items-center justify-between px-4 md:px-10 shadow-lg backdrop-blur-md">
      <h1 className="text-4xl font-bold">
        <Link href="/">FileShifter</Link>
      </h1>
      <div className="flex items-center min-w-fit">
        {!isLoggedIn ? ( // Use the prop for conditional rendering
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
            <Link href="/profile" passHref>
              <User className="hover:text-amber-200 cursor-pointer" />
            </Link>
            <LogoutButton />
          </div>
        )}
      </div>
    </div>
  );
}
