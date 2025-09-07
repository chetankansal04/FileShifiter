import PublicPage from "@/components/PublicPage"; 

export default function Home() {

  return (
    <div className="pt-20 flex min-h-dvh justify-center items-center">
      <PublicPage />
    </div>
  );
}


/**
 * If user is authenticated, redirect them to /dashboard.
 * Otherwise, render the home page.
 * @param {GetServerSidePropsContext} context - The context object
 */
export async function getServerSideProps(context) {
  const res = await fetch("http://65.0.95.86:8080/api/auth/me", {
    headers: { cookie: context.req.headers.cookie || "" }, // forward cookies to backend
  });

  if (res.ok) {
    return {
      redirect: {
        destination: "/dashboard",
        permanent: false,
      },
    };
  }

  return { props: {} };
}
