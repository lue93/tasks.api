
<code>aws dynamodb create-table --cli-input-json file://tasks_table.json --endpoint-url=http://localhost:4566</code>

<code>aws dynamodb list-tables --endpoint-url=http://localhost:4566</code>

<code>aws dynamodb scan --table-name=tasks --endpoint-url=http://localhost:4566 --output json</code>