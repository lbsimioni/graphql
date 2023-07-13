import Head from 'next/head';

import { useEffect, useState } from 'react';
import IOrder from '../models/order.model';
import api from '@/service/api';
import Image from 'next/image';

export default function Home() {

  const [orders, setOrders] = useState<IOrder[]>([]);

  useEffect(() => {
    api.post('http://174.101.101.103:8080/graphql', {
      query: 'query { placedOrders { order, items { name, image, qty, cost, currency }, total } }'
    })
      .then(response => {
        setOrders(response.data.data.placedOrders);
      })
  }, []);

  return (
    <>
      <Head>
        <title>Listagem de Pedidos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <main className='container'>
        <h2 className="text-center my-3">Listagem de Pedidos</h2>
        {orders.length === 0 && (
          <div className="alert alert-warning" role="alert">
            Nenhum pedido registrada! Faça a criação dos pedidos pela API, conforme indicado na documentação...
          </div>
        )}
        {orders.map(order => {
          return (
            <div className='card mb-3' key={order.order}>
              <div className='card-header'>
                {order.order}

                <div className='float-end'>
                  Total: {order.items[0].currency} {order.total.toFixed(2)}
                </div>
              </div>
              <div className='card-body'>
                <div className='row align-items-stretch'>
                  {order.items.map(item => {
                    return (
                      <div className='col-lg-3 col-md-4 col-sm-6' key={`${order.order}-${item.name}`}>
                        <div className='card'>
                          <div className='card-img-top'>
                            <Image src={item.image} fill sizes='100%, 100%' alt='Imagem do item' priority={false} />
                          </div>
                          <div className='card-body'>
                            <h5 className='card-title'>{item.name}</h5>
                            <p className='card-text'>Qty: {item.qty} <br />
                              Custo: {item.currency} {item.cost.toFixed(2)}<br />
                              <b>Custo total: {item.currency} {(item.qty * item.cost).toFixed(2)}</b>
                            </p>
                          </div>
                        </div>
                      </div>
                    )
                  })}
                </div>
              </div>
            </div>
          )
        })}
      </main>
    </>
  )
}
