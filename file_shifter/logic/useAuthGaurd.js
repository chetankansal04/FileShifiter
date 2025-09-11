import { useRouter } from "next/router";
import { useEffect } from "react";

export function useAuthGaurd() {
  const router = useRouter();

  useEffect(() => {
    // Define the async function inside the useEffect hook.
    const checkAuth = async () => {
      try {
        const res = await fetch("http://65.0.95.86:8080/api/protected", {
          method: "GET",
          credentials: "include", // This makes the browser send the cookie
        });

        if (res.status === 401) {
          // Authentication failed, redirect to login.
          console.log("Authentication failed. Redirecting to login page.");
          router.push("/login");
        }
      } catch (err) {
        // A network error occurred, so the user is not authenticated.
        console.error("Network error:", err);
        router.push("/login");
      }
    };
    
    // Call the async function.
    checkAuth();
  }, []); // The empty array ensures this effect runs only once on mount.
}