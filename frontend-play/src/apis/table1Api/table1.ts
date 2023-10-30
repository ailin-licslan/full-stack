export default {
    setup() {
        return {
            dataSource: [
                // {
                //     key: '1',
                //     name: 'xx',
                //     age: 32,
                //     sex: 1,
                // },
                // {
                //     key: '2',
                //     name: 'ff',
                //     age: 42,
                //     sex: 1,
                // },
            ],

            columns: [
                {
                    title: '姓名',
                    dataIndex: 'name',
                    key: 'name',
                },
                {
                    title: '年龄',
                    dataIndex: 'age',
                    key: 'age',
                },
                {
                    title: '性别',
                    dataIndex: 'sex',
                    key: 'sex',
                },
                {
                    title: '服务器运行的ip地址',
                    dataIndex: 'ip',
                    key: 'ip',
                },
                {
                    title: '服务器运行的端口号',
                    dataIndex: 'serverPort',
                    key: 'serverPort',
                },
            ],
        };
    },
};