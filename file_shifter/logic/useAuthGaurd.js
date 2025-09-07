import { useRouter } from "next/router";
import { useEffect } from "react";

export function useAuthGaurd() {
  const router = useRouter();

  useEffect(() => {
    const checkAuth = async () => {
      try {
        const res = await fetch("http://65.0.95.86:8080/api/protected", {
          method: "GET",
          credentials: "include",
        });
        if (res.status == 401) {
          router.push("/");
        }
      } catch (err) {
        console.error(err);
        router.push("/");
      }
    };
  }, [router]);
}

