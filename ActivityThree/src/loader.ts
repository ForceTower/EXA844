import axios from 'axios';
import { ContactResponse } from './types/ContactResponse';
import AmazonDynamo from "./helpers/AmazonDynamo";

const load = async () => {
    const response = await axios.get<ContactResponse>('https://8klio9atoh.execute-api.us-west-2.amazonaws.com/default/Contatos');
    const contacts = response.data.contatos;

    for (let i = 0; i < contacts.length; i++) {
        await AmazonDynamo.put(contacts[i])
    }

    return {
        statusCode: 200,
        body: JSON.stringify({
            message: 'Contents saved to Dynamo'
        }),
    };
};

const query = async () => {
    const data = await AmazonDynamo.getAll();
    const items = data.Items;
    return {
        statusCode: 200,
        body: JSON.stringify({
            message: 'Got elements from Dynamo',
            items
        }),
    };
};

export { load, query }
