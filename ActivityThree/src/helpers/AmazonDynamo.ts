import { DynamoDB } from 'aws-sdk';
const database = new DynamoDB.DocumentClient({
    region: 'us-east-1'
});

class AmazonDynamo {
    static put = (data: any) => {
        const params = {
            TableName: 'contacts-table',
            Item: data
        };

        return database.put(params).promise()
    };

    static getAll = () => {
        return database.scan({ TableName: 'contacts-table' }).promise();
    }
}

export default AmazonDynamo