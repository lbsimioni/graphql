interface IOrder {
    order: string;
    origin: string;
    items: IItem[];
    total: number;
    createdAt: string;
}

interface IItem {
    name: string;
    image: string;
    qty: number;
    cost: number;
    currency: string;
}

export default IOrder;