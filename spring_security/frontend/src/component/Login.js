import {  Button, Checkbox, Form, Input  } from 'antd';

const Login = _ => {
  const onFinish = (values) => {
    console.log('Success:', values);
  };
  
  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };
  return (
    <div style={{display: 'flex', justifyContent:'center', height: '100vh', alignItems: 'center', backgroundColor: '#d9d9d9'}}>
      <div style={{width: '60%'}}>
      <Form
      name="basic"
      labelCol={{ span: 6 }}
      wrapperCol={{ span: 12 }}
      style={{ maxWidth: 600 }}
      initialValues={{ remember: true }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
      autoComplete="off"
      >
      <Form.Item
      label="Username"
      name="username"
      rules={[{ required: true, message: 'Please input your username!' }]}
      >
      <Input />
      </Form.Item>
      
      <Form.Item
      label="Password"
      name="password"
      rules={[{ required: true, message: 'Please input your password!' }]}
      >
      <Input.Password />
      </Form.Item>
      
      <Form.Item name="remember" valuePropName="checked" wrapperCol={{ offset: 6, span: 12 }}>
      <Checkbox>Remember me</Checkbox>
      </Form.Item>
      
      <Form.Item wrapperCol={{ offset: 6, span: 12 }}>
      <Button type="primary" htmlType="submit">
      Submit
      </Button>
      </Form.Item>
      </Form>
      </div>
    </div>
  )
}

export default Login;