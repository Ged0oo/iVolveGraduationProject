terraform {
  backend "s3" {
    bucket = "nagdevops89"
    key    = "ivolve/terraform.tfstate"
    region = "us-east-1"
    encrypt = true
  }
}
 