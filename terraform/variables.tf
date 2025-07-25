variable "aws_region" {
  default = "us-west-1"
}

variable "vpc_cidr" {
  default = "10.0.0.0/16"
}

variable "public_subnet_cidr" {
  default = "10.0.1.0/24"
}

variable "instance_type" {
  default = "t2.micro"
}

variable "ami_id" {
  default = "ami-061ad72bc140532fd"
}

variable "key_name" {
  description = "SSH key name for EC2 instance"
  type        = string
}
