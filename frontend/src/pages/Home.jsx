import { Link } from "react-router-dom";
const Home=()=> <main>
<section className="bg-gradient-to-br from-blue-700 to-cyan-500 text-white px-6 py-24">
<div className="mx-auto max-w-6xl">
<h1 className="text-5xl font-black">Secure digital banking made simple.</h1>
<p className="mt-5 text-lg">Spring Boot + React banking system with accounts, transfers, fraud monitoring and audit logs.</p>
<div className="mt-8 flex gap-4"><Link className="rounded-xl bg-white px-6 py-3 font-bold text-primary" to="/register">Create Account</Link><Link className="rounded-xl border px-6 py-3 font-bold" to="/login">Login</Link></div>
</div>
</section>
<section className="mx-auto max-w-6xl px-6 py-16 grid gap-5 md:grid-cols-3">
{["JWT Security","Transactions","PDF Statements","Audit Logs","Fraud Rules","Admin Dashboard"].map(x=><div className="card p-6" key={x}><h3 className="font-bold">{x}</h3><p className="text-slate-500 mt-2">Production-style portfolio feature.</p></div>)}
</section>
</main>;
export default Home;
