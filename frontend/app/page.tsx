"use client";

import { useEffect, useState } from "react";

type Pedido = {
  id: number;
  cliente: string;
  productos: string;
  total: number;
};

type Factura = {
  id: number;
  proveedorId: number;
  pedidos: number[];
  totalFactura: number;
};

const API_A = "http://localhost:8080";
const API_B = "http://localhost:8082";

export default function Home() {
  const [pedidos, setPedidos] = useState<Pedido[]>([]);
  const [facturas, setFacturas] = useState<Factura[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function cargarDatos() {
      try {
        const [respPedidos, respFacturas] = await Promise.all([
          fetch(`${API_A}/pedidos`),
          fetch(`${API_B}/facturas`)
        ]);

        if (!respPedidos.ok || !respFacturas.ok) {
          throw new Error("Error llamando a los microservicios");
        }

        const pedidosData = await respPedidos.json();
        const facturasData = await respFacturas.json();

        setPedidos(pedidosData);
        setFacturas(facturasData);
      } catch (e: any) {
        setError(e.message ?? "Error desconocido");
      } finally {
        setLoading(false);
      }
    }

    cargarDatos();
  }, []);

  if (loading) {
    return (
      <main className="min-h-screen bg-slate-900 text-slate-100 flex items-center justify-center">
        <p className="text-xl">Cargando datos...</p>
      </main>
    );
  }

  if (error) {
    return (
      <main className="min-h-screen bg-slate-900 text-slate-100 flex items-center justify-center">
        <p className="text-red-400 text-xl">Error: {error}</p>
      </main>
    );
  }

  return (
    <main className="min-h-screen bg-slate-900 text-slate-100">
      <div className="max-w-5xl mx-auto py-10 space-y-10">
        <h1 className="text-3xl font-bold mb-4">MultiPedidos</h1>

        {/* Pedidos (Componente A) */}
        <section className="bg-slate-800/70 rounded-xl border border-slate-700 p-4">
          <h2 className="font-semibold mb-3">Pedidos (Componente A)</h2>
          <div className="overflow-x-auto text-sm">
            <table className="w-full border-collapse">
              <thead>
                <tr className="bg-slate-900/50">
                  <th className="px-3 py-2 text-left">ID</th>
                  <th className="px-3 py-2 text-left">Cliente</th>
                  <th className="px-3 py-2 text-left">Productos</th>
                  <th className="px-3 py-2 text-left">Total</th>
                </tr>
              </thead>
              <tbody>
                {pedidos.map((p) => (
                  <tr key={p.id} className="border-b border-slate-700/60">
                    <td className="px-3 py-2">{p.id}</td>
                    <td className="px-3 py-2">{p.cliente}</td>
                    <td className="px-3 py-2">{p.productos}</td>
                    <td className="px-3 py-2">{p.total}</td>
                  </tr>
                ))}
                {pedidos.length === 0 && (
                  <tr>
                    <td
                      className="px-3 py-2 text-center text-slate-500"
                      colSpan={4}
                    >
                      Aún no hay pedidos
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </section>

        {/* Facturas (Componente B) */}
        <section className="bg-slate-800/70 rounded-xl border border-slate-700 p-4">
          <h2 className="font-semibold mb-3">Facturas (Componente B)</h2>
          <div className="overflow-x-auto text-sm">
            <table className="w-full border-collapse">
              <thead>
                <tr className="bg-slate-900/50">
                  <th className="px-3 py-2 text-left">ID</th>
                  <th className="px-3 py-2 text-left">Proveedor</th>
                  <th className="px-3 py-2 text-left">Pedidos</th>
                  <th className="px-3 py-2 text-left">Total</th>
                </tr>
              </thead>
              <tbody>
                {facturas.map((f) => (
                  <tr key={f.id} className="border-b border-slate-700/60">
                    <td className="px-3 py-2">{f.id}</td>
                    <td className="px-3 py-2">{f.proveedorId}</td>
                    <td className="px-3 py-2">
                      {Array.isArray(f.pedidos) ? f.pedidos.join(", ") : ""}
                    </td>
                    <td className="px-3 py-2">{f.totalFactura}</td>
                  </tr>
                ))}
                {facturas.length === 0 && (
                  <tr>
                    <td
                      className="px-3 py-2 text-center text-slate-500"
                      colSpan={4}
                    >
                      Aún no hay facturas
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </main>
  );
}
